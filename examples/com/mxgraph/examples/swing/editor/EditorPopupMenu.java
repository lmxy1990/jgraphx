package com.mxgraph.examples.swing.editor;

import com.mxgraph.examples.swing.editor.EditorActions.HistoryAction;
import com.mxgraph.swing.util.mxGraphActions;
import com.mxgraph.util.mxResources;

import javax.swing.*;

public class EditorPopupMenu extends JPopupMenu {

    /**
     *
     */
    private static final long serialVersionUID = -3132749140550242191L;

    public EditorPopupMenu(BasicGraphEditor editor) {
        boolean selected = !editor.getGraphComponent().getGraph()
                .isSelectionEmpty();

        add(editor.bind(mxResources.get("undo"), new HistoryAction(true),
                "/images/undo.gif"));

        addSeparator();

        add(
                editor.bind(mxResources.get("cut"), TransferHandler
                                .getCutAction(),
                        "/images/cut.gif"))
                .setEnabled(selected);
        add(
                editor.bind(mxResources.get("copy"), TransferHandler
                                .getCopyAction(),
                        "/images/copy.gif"))
                .setEnabled(selected);
        add(editor.bind(mxResources.get("paste"), TransferHandler
                        .getPasteAction(),
                "/images/paste.gif"));

        addSeparator();

        add(
                editor.bind(mxResources.get("delete"), mxGraphActions
                                .getDeleteAction(),
                        "/images/delete.gif"))
                .setEnabled(selected);

        addSeparator();

        // Creates the format menu
        JMenu menu = (JMenu) add(new JMenu(mxResources.get("format")));

        EditorMenuBar.populateFormatMenu(menu, editor);

        // Creates the shape menu
        menu = (JMenu) add(new JMenu(mxResources.get("shape")));

        EditorMenuBar.populateShapeMenu(menu, editor);

        addSeparator();

        add(
                editor.bind(mxResources.get("edit"), mxGraphActions
                        .getEditAction())).setEnabled(selected);

        addSeparator();

        add(editor.bind(mxResources.get("selectVertices"), mxGraphActions
                .getSelectVerticesAction()));
        add(editor.bind(mxResources.get("selectEdges"), mxGraphActions
                .getSelectEdgesAction()));

        addSeparator();

        add(editor.bind(mxResources.get("selectAll"), mxGraphActions
                .getSelectAllAction()));
    }

}
