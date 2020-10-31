/*
 * Copyright © 2017-2020 Wayfair. All rights reserved.
 */
package com.wayfair.brickkit.size;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.wayfair.brickkit.BrickDataManager;
import com.wayfair.brickkit.R;

/**
 * This class defines an abstract implementation of brick size.
 *
 * It is used to determine the amount of the screen to use when laying out the brick. The amount
 * of screen that is used is relative to the max span count grabbed from the BrickDataManager.
 *
 * An example would be a brick whose size was 2 that was being laid out in a BrickDataManager whose max span count
 * is 5. The brick would take 40% (2 / 5) of the screen width.
 */
public abstract class BrickSize {
    /**
     * Calculates the spans for this brick based off the device type and orientation.
     *
     * @param context the context to use to get resources
     * @return the number of spans this brick will take up
     */
    public int getSpans(Context context) {
        int spans;

        if (context.getResources().getBoolean(R.bool.tablet)) {
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                spans = landscapeTablet();
            } else {
                spans = portraitTablet();
            }
        } else {
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                spans = landscapePhone();
            } else {
                spans = portraitPhone();
            }
        }

        if (spans > BrickDataManager.SPAN_COUNT) {
            Log.i(getClass().getSimpleName(), "Span needs to be less than or equal to: " + BrickDataManager.SPAN_COUNT);
            spans = BrickDataManager.SPAN_COUNT;
        }

        return spans;
    }

    /**
     * Method to return the size to use for this brick on tablets in landscape orientation.
     *
     * @return size to use for this brick on tablets in landscape orientation.
     */
    protected abstract int landscapeTablet();

    /**
     * Method to return the size to use for this brick on tablets in portrait orientation.
     *
     * @return size to use for this brick on tablets in portrait orientation.
     */
    protected abstract int portraitTablet();

    /**
     * Method to return the size to use for this brick on phones in landscape orientation.
     *
     * @return size to use for this brick on phones in landscape orientation.
     */
    protected abstract int landscapePhone();

    /**
     * Method to return the size to use for this brick on phones in portrait orientation.
     *
     * @return size to use for this brick on phones in portrait orientation.
     */
    protected abstract int portraitPhone();
}
