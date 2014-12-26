package ut.com.yonyou.sdp;

import org.junit.Test;
import com.yonyou.sdp.MyPluginComponent;
import com.yonyou.sdp.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}