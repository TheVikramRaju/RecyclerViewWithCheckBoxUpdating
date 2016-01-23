package com.vikram.recyclerviewwithcheckboxupdating;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private List<Units> unitsList;

    private Button btnSelection;
    private int Max =100;
    int count =0;
    String checkedItems = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSelection = (Button) findViewById(R.id.btnShow);

        unitsList = new ArrayList<Units>();

        for (int i = 1; i < 11; i++) {
            Units st = new Units("Unit"+i,0,false);

            unitsList.add(st);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter
        mAdapter = new CardViewDataAdapter(unitsList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);

        btnSelection.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = "";
                List<Units> stList = ((CardViewDataAdapter) mAdapter)
                        .getSelectedUnitsist();

                for (int i = 0; i < stList.size(); i++) {
                    Units singleUnits = stList.get(i);
                    if (singleUnits.isSelected() == true) {
                        data = data + "\n" + singleUnits.getNoOfquestions();
                    }
                }

                Toast.makeText(MainActivity.this,
                        "Selected Units Question: \n" + data , Toast.LENGTH_LONG)
                        .show();
            }
        });

    }



    public class CardViewDataAdapter extends RecyclerView.Adapter<CardViewDataAdapter.ViewHolder> {
        private List<Units> stList;

        public CardViewDataAdapter(List<Units> unitses) {
            this.stList = unitses;
        }

        // Create new views
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
            // create a new view
            View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.cardview_row, null);

            // create ViewHolder
            ViewHolder viewHolder = new ViewHolder(itemLayoutView);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

            final int pos = position;
            viewHolder.tvName.setText(stList.get(position).getStrUnitName());
            viewHolder.tvValues.setText(stList.get(position).getNoOfquestions() + "");
            viewHolder.chkSelected.setChecked(stList.get(position).isSelected());
            viewHolder.seekBar.setProgress(stList.get(position).getNoOfquestions());
            viewHolder.chkSelected.setTag(stList.get(position));

            viewHolder.chkSelected.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkedItems = "";
                    count = 0;
                    CheckBox cb = (CheckBox) v;
                    Units contact = (Units) cb.getTag();

                    contact.setIsSelected(cb.isChecked());
                    for (int i = 0; i < stList.size(); i++) {
                        if (stList.get(i).isSelected()) {
//                            checkedItems = checkedItems + stList.get(i).getStrUnitName() + ",";
                            count++;
                        }
                    }
//                    Toast.makeText(v.getContext(), checkedItems, Toast.LENGTH_LONG).show();
                    int questionPerUnit = 0;
                    int reminder = 0;
                    if (count != 0) {
                        questionPerUnit = Max / count;
                        reminder = Max % count;
                    }
                    for (int i = 0; i < stList.size(); i++) {
                        if (stList.get(i).isSelected()) {
                            stList.get(i).setNoOfquestions(questionPerUnit);
                        } else {
                            stList.get(i).setNoOfquestions(0);
                        }
                    }
                    for (int i = 0; i < reminder; i++) {
                        if (stList.get(i).isSelected()) {
                            int previous = stList.get(i).getNoOfquestions();
                            stList.get(i).setNoOfquestions(previous + 1);
                        }
                    }
                    notifyDataSetChanged();

                }
            });

            viewHolder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                public void onStopTrackingTouch(SeekBar bar) {
                    int value = bar.getProgress();
                    stList.get(position).setNoOfquestions(value);

                }

                public void onStartTrackingTouch(SeekBar bar) {

                }

                public void onProgressChanged(SeekBar bar,
                                              int paramInt, boolean paramBoolean) {
                    //update textview while dragging seekbar
                    viewHolder.tvValues.setText("" + paramInt); // here in textView the percent will be shown

                }
            });

        }
        // Return the size arraylist
        @Override
        public int getItemCount() {
            return stList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvName;
            public TextView tvValues;
            public CheckBox chkSelected;
            public SeekBar seekBar;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                tvName = (TextView) itemLayoutView.findViewById(R.id.selected_unit_title);
                tvValues = (TextView) itemLayoutView.findViewById(R.id.selected_ques_count);
                chkSelected = (CheckBox) itemLayoutView
                        .findViewById(R.id.chk_item);
                seekBar = (SeekBar) itemLayoutView.findViewById(R.id.range);
                seekBar.setMax(Max);

            }
        }
        // method to access in activity after updating selection
        public List<Units> getSelectedUnitsist() {
            return stList;
        }

    }


}
