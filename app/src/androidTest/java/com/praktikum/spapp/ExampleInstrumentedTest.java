package com.praktikum.spapp;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.praktikum.spapp", appContext.getPackageName());
    }
}
