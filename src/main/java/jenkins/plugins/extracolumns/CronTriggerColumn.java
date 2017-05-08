/*
 * The MIT License
 *
 * Copyright (c) 2017, Sanketh Indarapu
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

import java.util.Map;
import java.util.Calendar;
import java.text.DateFormat;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.AbstractProject;

import hudson.views.ListViewColumnDescriptor;
import hudson.views.ListViewColumn;

import hudson.triggers.Trigger;
import hudson.triggers.TimerTrigger;
import hudson.triggers.TriggerDescriptor;

import hudson.scheduler.CronTabList;
import hudson.scheduler.Hash;

public class CronTriggerColumn extends ListViewColumn {

    public final static String DEFAULT_TRIGGER = "N/A";

    @DataBoundConstructor
    public CronTriggerColumn() {
        super();
    }


    public String getCronTrigger(@SuppressWarnings("rawtypes") Job job) {
	String cronTrigger = DEFAULT_TRIGGER;
        if (job == null) {
	    return cronTrigger;
        }

	AbstractProject project = (AbstractProject)job;
	@SuppressWarnings("unchecked")
	Map<TriggerDescriptor, Trigger> triggers = project.getTriggers();

	for (Trigger trigger : triggers.values()) {
	    if (trigger instanceof TimerTrigger) {
		cronTrigger = trigger.getSpec();
		// This will look like a cron spec of the form
		// 10 1 * * *
	    }
	}

	return cronTrigger;
    }

    public String getCronTriggerToolTip(@SuppressWarnings("rawtypes") Job job) {
	String toolTip = DEFAULT_TRIGGER;
	
	if (job == null) {
	    return toolTip;
	}
	
	String cronTrigger = getCronTrigger(job);
	if (cronTrigger == null || cronTrigger.isEmpty() || cronTrigger == DEFAULT_TRIGGER) {
	    return toolTip;
	}

	try {
	    // The logic here follows the one used in TimerTrigger to show a similar
	    // message in the job configuration page
	    CronTabList ctl = CronTabList.create(cronTrigger, Hash.from(job.getFullName()));
	    if (ctl == null) {
		return toolTip;
	    }
	    DateFormat fmt = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
	    Calendar previous = ctl.previous();
	    Calendar next = ctl.next();
	    if (previous == null || next == null) {
		return toolTip;
	    }
	    return Messages.CronTriggerColumn_ToolTipFormat(fmt.format(previous.getTime()), fmt.format(next.getTime()));
	} catch (antlr.ANTLRException ex) {
	    // ignore
	}

	return toolTip;
    }

    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {

        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
            return Messages.CronTriggerColumn_DisplayName();
        }

        @Override
        public String getHelpFile() {
            return "/plugin/extra-columns/help-cron-trigger-column.html";
        }

    }
}
