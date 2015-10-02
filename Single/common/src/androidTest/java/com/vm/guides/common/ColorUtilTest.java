package com.vm.guides.common;

import android.support.test.runner.AndroidJUnit4;

import com.vm.guides.common.util.ColorUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

@RunWith(AndroidJUnit4.class)
public class ColorUtilTest {

    @Test
    public void randomColor_isRandom() throws Exception {

        int color1 = ColorUtil.randomColor();
        int color2 = ColorUtil.randomColor();
        assertNotEquals(color1, color2);
    }
}