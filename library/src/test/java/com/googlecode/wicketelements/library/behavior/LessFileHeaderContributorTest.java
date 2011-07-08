package com.googlecode.wicketelements.library.behavior;

import org.apache.wicket.markup.html.IHeaderContributor;
import org.testng.annotations.Test;

@Test
public class LessFileHeaderContributorTest {
    public void testContributor() {
        final IHeaderContributor contributor = new LessFileHeaderContributor(LessFileHeaderContributor.class, "testfile.less");
    }
}
