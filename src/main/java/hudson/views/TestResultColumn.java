package hudson.views;


import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import hudson.views.Messages;

public class TestResultColumn extends ListViewColumn {

	@DataBoundConstructor
	public TestResultColumn() {
	}

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        @Override
        public String getDisplayName() {
            return "Test Result";
//            return Messages.TestResultColumn_DisplayName();
        }
    }

}
