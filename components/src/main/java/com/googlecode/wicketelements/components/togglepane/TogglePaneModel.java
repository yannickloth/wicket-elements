package com.googlecode.wicketelements.components.togglepane;

import com.googlecode.jbp.common.requirements.Reqs;

import java.util.List;

public class TogglePaneModel {
    private List<AbstractPane> panes;
    private List<AbstractPane> explodedPanes;
    private boolean allowMultipleExplodedPanes;

    public void togglePane(final AbstractPane paneParam) {
        Reqs.PARAM_REQ.Object.requireNotNull(paneParam, "The pane that must be toggled must not be null.");
        if (!paneParam.isExploded()) {
            if (!allowMultipleExplodedPanes) {
                collapseAllActivePanes();
            }
            explodePane(paneParam);
        } else {
            collapsePane(paneParam);
        }
    }

    private boolean isExplodedPane(final AbstractPane paneParam) {
        return explodedPanes.contains(paneParam);
    }

    private void explodePane(final AbstractPane paneParam) {
        Reqs.GENERIC_REQ.Logic.requireFalse(explodedPanes.contains(paneParam), "The pane that must be exploded must be collapsed.");
        paneParam.toggleContent();
        explodedPanes.add(paneParam);
    }

    private void collapsePane(final AbstractPane paneParam) {
        Reqs.GENERIC_REQ.Logic.requireTrue(explodedPanes.contains(paneParam), "The pane that must be collapsed must be exploded.");
        paneParam.toggleContent();
        explodedPanes.remove(paneParam);
    }

    private void collapseAllActivePanes() {
        for (final AbstractPane currentPane : explodedPanes) {
            collapsePane(currentPane);
        }
    }
}
