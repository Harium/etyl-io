package com.harium.etyl.util.io;

import org.junit.Assert;
import org.junit.Test;

public class IOHelperTest {

    @Test
    public void testStartsWithHttp() {
        String path = "http://localhost";
        Assert.assertTrue(IOHelper.startsWithHttp(path));
    }


}
