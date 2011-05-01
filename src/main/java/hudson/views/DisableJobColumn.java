package hudson.views;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;

public class DisableJobColumn extends ListViewColumn {

	@DataBoundConstructor
	public DisableJobColumn() {
	}

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        @Override
        public String getDisplayName() {
            return "Disable Job";
        }
    }

}
