package jenkins.plugins.extracolumns;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;

public class DisableProjectColumn extends ListViewColumn {

    @DataBoundConstructor
    public DisableProjectColumn() {
    }

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        @Override
        public String getDisplayName() {
            return Messages.DisableProjectColumn_DisplayName();
        }
        
        public boolean shownByDefault() {
            return false;
        }
    }

}
