package jenkins.plugins.extracolumns;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;

public class TestResultColumn extends ListViewColumn {

    @DataBoundConstructor
    public TestResultColumn() {
    }

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        @Override
        public String getDisplayName() {
            return Messages.TestResultColumn_DisplayName();
        }
        
        public boolean shownByDefault() {
            return false;
        }
    }

}
