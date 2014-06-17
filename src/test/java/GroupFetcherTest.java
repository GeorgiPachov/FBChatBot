import static org.junit.Assert.assertNotEquals;
import junit.framework.TestCase;

import org.junit.Test;

import com.gpachov.fb.messenger.Credentials;
import com.gpachov.fb.messenger.GroupFetcher;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;


public class GroupFetcherTest extends TestCase {

    private GroupFetcher groupFetcher;

    protected void setUp() throws Exception {
        final FacebookClient facebookClient = new DefaultFacebookClient(Credentials.ACCESS_TOKEN);
        this.groupFetcher = new GroupFetcher(facebookClient, Credentials.GROUP_ID);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    @Test
    public void testGroupFetching() throws Exception {
        System.out.println(groupFetcher.toString().length());
        System.out.println(groupFetcher.toString());
        
    }

}
