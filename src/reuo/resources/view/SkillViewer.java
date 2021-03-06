package reuo.resources.view;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;

import reuo.resources.Skill;
import reuo.resources.io.*;

public class SkillViewer extends Viewer<SkillLoader> {
	JTable table;
	SkillLoader loader;
	LoaderModel<Skill> model;

	public SkillViewer(File dir, String[] fileNames) throws FileNotFoundException, IOException {
		loader = new SkillLoader();
		prepareLoader(dir, fileNames);

		setupDefaultStatusBar();

		model = new LoaderModel<Skill>(loader);
		table = new JTable(new FieldTableModel(model, Skill.class, "Action:isAction", "Name:getName"));
		table.getSelectionModel().addListSelectionListener(this);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(32);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);

		add(new JScrollPane(table));
	}

	@Override
	public void prepareLoader(File dir, String[] fileNames) throws FileNotFoundException, IOException {
		loader.prepare(new StoredIndexPreparation<Preparation.None>(new File(dir, fileNames[0]), new File(dir, fileNames[1]), null, null));
	}

	public void valueChanged(ListSelectionEvent e) {
		updateStatusIDs(model.getId(table.getSelectedRow()));
	}

	@Override
	public String getStatusConstraints() {
		return "0[fill, 50%]0";
	}
	
	@Override
	public void restore() {
		System.out.println("!");
		table.getColumnModel().getColumn(0).setPreferredWidth(55);
		table.doLayout();
	}
	
}
