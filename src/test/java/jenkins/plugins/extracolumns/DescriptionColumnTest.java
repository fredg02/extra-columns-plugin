/*
 * The MIT License
 *
 * Copyright (c) 2011, Axel Haustant
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

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.SortedMap;

import hudson.model.Job;
import hudson.model.Run;

import org.junit.Before;
import org.junit.Test;

public class DescriptionColumnTest {

    private final static String SIMPLE_DESCRIPTION = "Just a test";

    private final static String MULTILINE_DESCRIPTION = "Just a test<br/>Another Line<br>One <b>more</b> line<BR>Last line";

    private Job job;

    @SuppressWarnings("rawtypes")
    private static class TestJob extends Job {

        private String description;

        protected TestJob(String name) {
            super(null, name);
        }

        @Override
        protected SortedMap _getRuns() {
            return null;
        }

        @Override
        public boolean isBuildable() {
            return false;
        }

        @Override
        protected void removeRun(Run arg0) {

        }

        @Override
        public void setDescription(String description) throws IOException {
            this.description = description;
        }

        @Override
        public String getDescription() {
            return description;
        }

    }

    @Before
    public void setUp() {
        job = new TestJob("My Job");
    }

    @Test
    public void formatWithoutTrimming() throws IOException {
        DescriptionColumn plugin = new DescriptionColumn(false, false, 2);
        job.setDescription(SIMPLE_DESCRIPTION);
        String result = plugin.getDescription(job);
        assertEquals(SIMPLE_DESCRIPTION, result);
        job.setDescription(MULTILINE_DESCRIPTION);
        result = plugin.getDescription(job);
        assertEquals(MULTILINE_DESCRIPTION, result);
    }

    @Test
    public void formatWithTrimming() throws IOException {
        DescriptionColumn plugin = new DescriptionColumn(false, true, 2);
        job.setDescription(SIMPLE_DESCRIPTION);
        String result = plugin.getDescription(job);
        assertEquals(SIMPLE_DESCRIPTION, result);
        job.setDescription(MULTILINE_DESCRIPTION);
        result = plugin.getDescription(job);
        assertEquals("Just a test<br/>Another Line", result);
        plugin = new DescriptionColumn(false, true, 7);
        result = plugin.getDescription(job);
        assertEquals("Just a test<br/>Another Line<br/>One <b>more</b> line<br/>Last line", result);
    }

    @Test
    public void displayName() throws IOException {
        DescriptionColumn plugin = new DescriptionColumn(true, false, 2);
        job.setDescription(SIMPLE_DESCRIPTION);
        String result = plugin.getDescription(job);
        assertEquals("Just a test", result);
    }
}