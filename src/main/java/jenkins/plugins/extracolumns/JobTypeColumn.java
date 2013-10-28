/*
 * The MIT License
 *
 * Copyright (c) 2013, Frederic Gurr
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package jenkins.plugins.extracolumns;

import jenkins.model.Jenkins;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.ViewJob;
import hudson.model.AbstractItem;
import hudson.util.VersionNumber;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;

public class JobTypeColumn extends ListViewColumn {

    private boolean usePronoun;
    
    @DataBoundConstructor
    public JobTypeColumn(boolean usePronoun) {
        super();
        this.usePronoun = usePronoun;
    }

    public JobTypeColumn() {
        this(false);
    }

    public boolean usePronoun(){
        return usePronoun;
    }

    /**
     * Returns the job type <br/>
     * 
     * <p><b>Note:</b> With Jenkins version 1.519 and higher, an option appears in the config section for this column
     * that allows to use the 'pronoun' instead of the 'simple name' to query the job type.</p>
     * 
     * @param item
     * @return job type
     */
    public String getJobType(AbstractItem item) {
        if (usePronoun()) {
            return item.getPronoun();
        } else {
            return getSimpleName(item);
        }
    }

    private String getSimpleName(AbstractItem item){
        String simpleName = item.getClass().getSimpleName();
        if("Folder".equals(simpleName)){
            return Messages.JobTypeColumn_FolderName();
        } else if ("MavenModuleSet".equals(simpleName)) {
            return Messages.JobTypeColumn_MavenName();
        } else if ("MultiJobProject".equals(simpleName)) {
            return Messages.JobTypeColumn_MultiJobName();
        } else if ("IvyModuleSet".equals(simpleName)) {
            return Messages.JobTypeColumn_IvyName();
        } else if ("MatrixProject".equals(simpleName)) {
            return Messages.JobTypeColumn_MultiConfigName();
        } else if ("FreeStyleProject".equals(simpleName)) {
            return Messages.JobTypeColumn_FreestyleName();
        } else if (item instanceof ViewJob) {
            return Messages.JobTypeColumn_ExternalName();
        }
        return "";
    }

    public boolean isVersion1519(){
        VersionNumber vn1519 = new VersionNumber("1.519");
        return Jenkins.getVersion().equals(vn1519) || Jenkins.getVersion().isNewerThan(vn1519) ;
    }

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {

        public DescriptorImpl() {
        }

        @Override
        public String getDisplayName() {
            return Messages.JobTypeColumn_DisplayName();
        }

        public boolean shownByDefault() {
            return false;
        }

    }

}
