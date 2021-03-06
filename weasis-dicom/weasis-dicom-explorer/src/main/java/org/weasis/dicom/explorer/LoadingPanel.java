package org.weasis.dicom.explorer;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LoadingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final ArrayList<ExplorerTask> tasks = new ArrayList<ExplorerTask>();
    private final LoadingTaskPanel globalDownloadTask = new LoadingTaskPanel(true);

    public LoadingPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setAlignmentY(TOP_ALIGNMENT);
    }

    public boolean addTask(ExplorerTask task) {
        boolean update = false;
        if (task != null && !tasks.contains(task)) {
            tasks.add(task);
            if (task.isSubTask()) {
                if (getComponentZOrder(globalDownloadTask) == -1) {
                    this.add(globalDownloadTask);
                    update = true;
                }
                globalDownloadTask.setMessage(task.getMessage());
            } else {
                JPanel taskPanel = new LoadingTaskPanel(task);
                this.add(taskPanel);
                update = true;
            }
        }
        return update;
    }

    public boolean removeTask(ExplorerTask task) {
        boolean update = false;
        if (task != null) {
            tasks.remove(task);
            if (task.isSubTask()) {
                if (getDownloadTaskNumber() == 0) {
                    this.remove(globalDownloadTask);
                    update = true;
                }
            } else {
                for (Component c : getComponents()) {
                    if (c instanceof LoadingTaskPanel && task.equals(((LoadingTaskPanel) c).getTask())) {
                        remove(c);
                        update = true;
                    }
                }
            }
        }

        return update;
    }

    public int getDownloadTaskNumber() {
        int i = 0;
        for (ExplorerTask explorerTask : tasks) {
            if (explorerTask.isSubTask()) {
                i++;
            }
        }
        return i;
    }
}
