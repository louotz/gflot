package com.googlecode.gflot.examples.client.examples.sliding;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.PlotModelStrategy;
import com.googlecode.gflot.client.PlotWidget;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.GlobalSeriesOptions;
import com.googlecode.gflot.client.options.LegendOptions;
import com.googlecode.gflot.client.options.LineSeriesOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.PointsSeriesOptions;
import com.googlecode.gflot.client.options.TimeSeriesAxisOptions;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( SlidingPlace.UI_RAW_SOURCE_FILENAME )
public class SlidingExample
    extends DefaultActivity
{
    /**
     * Refresh period
     */
    @GFlotExamplesData
    private static final int period = 300;
    /**
     * Maximum datapoints in the slide at a time
     */
    @GFlotExamplesData
    private static final int slidingWindow = 20;

    /**
     * The Async interface of the service
     */
    @GFlotExamplesSource
    interface FakeRpcServiceAsync
    {
        void getNewData( AsyncCallback<DataPoint[]> callback );
    }

    interface Binder
        extends UiBinder<Widget, SlidingExample>
    {
    }

    private static Binder binder = GWT.create( Binder.class );
    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField(provided = true)
    SimplePlot plot;
    /**
     * start/stop button
     */
    @GFlotExamplesData
    @UiField
    Button startStop;
    /**
     * Timer
     */
    @GFlotExamplesData
    private Timer updater;

    public SlidingExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createPlot()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = PlotOptions.create();
        plotOptions.setGlobalSeriesOptions( GlobalSeriesOptions.create().setLineSeriesOptions( LineSeriesOptions.create().setLineWidth( 1 )
            .setShow( true ).setFill( true ).setFillColor( 1.0, 1.0, 1.0, 0.5 ) ).setPointsOptions( PointsSeriesOptions.create()
            .setRadius( 1 ).setShow( true ) ).setShadowSize( 0d ) ).setLegendOptions( LegendOptions.create().setShow( false ) );
        plotOptions.addXAxisOptions( TimeSeriesAxisOptions.create().setTimeZone( TimeSeriesAxisOptions.TIME_ZONE_BROWSER_KEY ) );

        final SeriesHandler series = model.addSeries( Series.of( "Random Series", "#FF9900" ), PlotModelStrategy
            .slidingWindowStrategy( slidingWindow ) );

        // pull the "fake" RPC service for new data
        updater = new Timer()
        {
            @Override
            public void run()
            {
                update( series, plot );
            }
        };

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

    /**
     * Start the timer when the activity starts
     */
    @GFlotExamplesSource
    @Override
    public void start( AcceptsOneWidget panel, EventBus eventBus )
    {
        super.start( panel, eventBus );
        start();
        fillSlidingWindow( plot.getModel().getHandlers().get( 0 ) );
    }

    /**
     * Fill the series with random data
     */
    @GFlotExamplesSource
    private void fillSlidingWindow( SeriesHandler series )
    {
        series.clear();
        double currentMilli = System.currentTimeMillis();
        for ( int i = slidingWindow - 1; i >= 0; i-- )
        {
            series.add( DataPoint.of( currentMilli - (period * i), nextValue() ) );
        }
    }

    /**
     * Stop the timer when the activity stops
     */
    @GFlotExamplesSource
    @Override
    public void onStop()
    {
        stop();
        plot.getModel().clear();
        super.onStop();
    }

    /**
     * On click on the start/stop button
     */
    @GFlotExamplesSource
    @UiHandler("startStop")
    void onClickStartStop( ClickEvent e )
    {
        if ( "Stop".equals( startStop.getText() ) )
        {
            stop();
        }
        else
        {
            start();
        }
    }

    /**
     * Start the timer
     */
    @GFlotExamplesSource
    private void start()
    {
        startStop.setText( "Stop" );
        updater.scheduleRepeating( period );
    }

    /**
     * Stop the timer
     */
    @GFlotExamplesSource
    private void stop()
    {
        startStop.setText( "Start" );
        updater.cancel();
    }

    /**
     * Fake a rpc call and update the data
     */
    @GFlotExamplesSource
    private void update( final SeriesHandler series, final PlotWidget plot )
    {
        FakeRpcServiceAsync service = getRpcService();
        service.getNewData( new AsyncCallback<DataPoint[]>()
        {
            public void onFailure( Throwable caught )
            {
                GWT.log( "Something went wrong", caught );
            }

            public void onSuccess( DataPoint[] result )
            {
                for ( DataPoint dataPoint : result )
                {
                    series.add( dataPoint );
                }
                plot.redraw();
            }
        } );
    }

    /**
     * @return a fake rpc service
     */
    @GFlotExamplesSource
    private FakeRpcServiceAsync getRpcService()
    {
        return new FakeRpcServiceAsync()
        {
            public void getNewData( final AsyncCallback<DataPoint[]> callback )
            {
                callback.onSuccess( new DataPoint[] { DataPoint.of( System.currentTimeMillis(), nextValue() ) } );
            }
        };
    }

    /**
     * @return the next random value
     */
    @GFlotExamplesSource
    private double nextValue()
    {
        return Math.min( Random.nextDouble() + 0.2, 0.9 );
    }
}
