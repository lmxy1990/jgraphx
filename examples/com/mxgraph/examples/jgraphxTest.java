package com.mxgraph.examples;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class jgraphxTest {

    static final int width = 80;
    static final int height = 30;
    static final int xMove = 200;
    static final int yMove = 60;

    private static final JFrame frame = new JFrame("Call Tree");

    static {
        // 创建调用链路图
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400, 300));
        // 设置 preferredSize
        frame.setPreferredSize(new Dimension(800, 600));
        // 居中
        frame.setLocationRelativeTo(null);
        // 显示
        frame.setVisible(false);
    }


    private static void clearFrameContent(JFrame frame) {
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            frame.remove(component);
        }
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {


        mxGraph graph = new mxGraph();
        Object rootNode = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(rootNode, null, "Start-Start-Start-Start-Start-Start-Start", 20, 20, 80, 30);
            Object v2 = graph.insertVertex(rootNode, null, "Process-Process-Process-Process-Process-Process-Process", 240, 150, 80, 30);
            Object v3 = graph.insertVertex(rootNode, null, "End-End-End-End-End-End-End-End-End-End-End-End-End", 460, 20, 80, 30);


            // 连接流程图中的不同部分
            graph.insertEdge(rootNode, null, "线条11111111111111111", v1, v2);
            graph.insertEdge(rootNode, null, "线条222222222222222", v2, v3);
        } finally {
            graph.getModel().endUpdate();
        }

        // 禁止编辑
        graph.setCellsEditable(false);
        // 禁止断开
        graph.setCellsDisconnectable(false);
        // 禁止复制
        graph.setCellsCloneable(false);
        // 禁止连线
        graph.setConnectableEdges(false);
        // 禁止拖动
        graph.setAllowDanglingEdges(false);
        // 禁止删除
        graph.setDropEnabled(false);
        // 背景图片
        graph.setKeepEdgesInBackground(true);
        // 可以改变大小
        graph.setCellsResizable(true);
        // 可以移动
        graph.setCellsMovable(true);


        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        // 禁止连接
        graphComponent.setConnectable(false);
        // 启用自动滚动
        graphComponent.setAutoScroll(true);
        // 启用拖动节点
        graphComponent.setDragEnabled(true);

        graphComponent.setInvokesStopCellEditing(true);

        // 添加双击事件
        ComponentListener[] componentListeners = graphComponent.getGraphControl().getComponentListeners();
        System.out.println(componentListeners);
        graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("mouseClicked");
                if (e.getClickCount() != 2) {
                    return;
                }
                mxCell cell = (mxCell) graph.getSelectionCell();
                if (cell == null) {
                    return;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mousePressed");
                mxCell cell = (mxCell) graph.getSelectionCell();
                if (cell != null && cell.isEdge()) {
                    e.consume();
                }
                super.mousePressed(e);
            }
        });
        // 展示
        clearFrameContent(frame);
        frame.add(graphComponent);
        // 自动调整大小
        frame.pack();
        frame.setVisible(true);


    }


}
