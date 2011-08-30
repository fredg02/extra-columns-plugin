package jenkins.plugins.extracolumns;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.Items;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;

public class ConfigureProjectColumn extends ListViewColumn {

    @DataBoundConstructor
    public ConfigureProjectColumn() {
    }

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        
        public DescriptorImpl() {
//            Items.XSTREAM.addCompatibilityAlias("hudson.views.ConfigureProjectColumn", jenkins.plugins.extracolumns.ConfigureProjectColumn.class);
        }
        
        @Override
        public String getDisplayName() {
            return Messages.ConfigureProjectColumn_DisplayName();
        }
        
        public boolean shownByDefault() {
            return true;
        }
    }

}
