package hudson.views;


import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;

public class ConfigureShortcutColumn extends ListViewColumn {

	@DataBoundConstructor
	public ConfigureShortcutColumn() {
	}

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        @Override
        public String getDisplayName() {
            return "Configure Shortcut";
        }
    }

}
