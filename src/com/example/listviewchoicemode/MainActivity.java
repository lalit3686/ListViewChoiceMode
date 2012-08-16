package com.example.listviewchoicemode;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lView;
	private Button button;
	private String lv_items[] = { "Android", "iPhone", "BlackBerry",
			"AndroidPeople", "J2ME", "Listview", "ArrayAdapter", "ListItem",
			"Us", "UK", "India" };
	private ArrayList<String> selectedItems = new ArrayList<String>();

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_main);

		lView = (ListView) findViewById(R.id.ListView01);
		button = (Button) findViewById(R.id.btn_items);

		lView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, lv_items));
		lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); // setting choice mode here

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SparseBooleanArray checked = lView.getCheckedItemPositions();
				for (int i = 0; i < lView.getCount(); i++) {
					String item = lView.getAdapter().getItem(i).toString();
					if (checked.get(i)) {
						// add if item is not present in ArrayList
						if (!selectedItems.contains(item))
							selectedItems.add(item);
					} else {
						// remove item if it is present in ArrayList
						if (selectedItems.contains(item))
							selectedItems.remove(item);
					}
				}
				if (selectedItems.size() > 0)
					Toast.makeText(v.getContext(), selectedItems.toString(),
							Toast.LENGTH_LONG).show();
				else
					Toast.makeText(v.getContext(), "No Items Selected.",
							Toast.LENGTH_LONG).show();
			}
		});
	}
}
