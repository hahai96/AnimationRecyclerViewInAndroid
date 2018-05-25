package com.example.ha_hai.animationinandroid.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ha_hai.animationinandroid.R;
import com.example.ha_hai.animationinandroid.adapter.FoodAdapter;
import com.example.ha_hai.animationinandroid.helper.ItemOffsetDecoration;
import com.example.ha_hai.animationinandroid.helper.RecyclerItemTouchHelper;
import com.example.ha_hai.animationinandroid.model.AnimationItem;
import com.example.ha_hai.animationinandroid.model.Food;
import com.example.ha_hai.animationinandroid.model.ItemTouchDragHelper;
import com.example.ha_hai.animationinandroid.model.ItemTouchMoveHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements ItemTouchMoveHelper, ItemTouchDragHelper{

    Spinner spinner;
    RecyclerView recyclerView;
    AnimationItem[] items;
    int itemSelected;

    ArrayList<Food> foodList;
    FoodAdapter adapter;

    public BaseFragment() {
        // Required empty public constructor
    }

    public abstract int getLayoutId();

    public abstract RecyclerView.LayoutManager getLayoutManager();

    public abstract AnimationItem[] getAnimationItems();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.spinner);
        recyclerView = view.findViewById(R.id.recyclerView);
        view.findViewById(R.id.btnReload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runLayoutAnimation(items[itemSelected]);
            }
        });

        prepareFoods();
        setupSpinner();
        setupRecyclerView();
        runLayoutAnimation(items[0]);
    }

    private void runLayoutAnimation(AnimationItem item) {
        LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getActivity(), item.getAnim());

        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private void setupRecyclerView() {
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing_item_offset_decoration);
        recyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(getLayoutManager());

        RecyclerItemTouchHelper itemTouchHelperCallback = new RecyclerItemTouchHelper(this, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        adapter = new FoodAdapter(getActivity(), foodList, getLayoutManager());
        recyclerView.setAdapter(adapter);
    }

    private void setupSpinner() {
        ArrayList<String> itemNames = new ArrayList<>();
        items = getAnimationItems();
        for (AnimationItem i: items) {
            itemNames.add(i.getTitle());
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemNames);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                itemSelected = i;
                runLayoutAnimation(items[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void prepareFoods() {
        foodList = new ArrayList<>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Food");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    foodList.add(snapshot.getValue(Food.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemMove(final int position) {
        int deletedPosition = position;
        final Food food = foodList.get(position);
        String name = foodList.get(position).getName();

        foodList.remove(deletedPosition);
        adapter.notifyItemRemoved(deletedPosition);

        Snackbar snackbar = Snackbar.make(recyclerView, name + " removed from cart!", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemRestore(food, position);
            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    @Override
    public void onItemRestore(Food food, int position) {
        foodList.add(position, food);
        adapter.notifyItemInserted(position);
    }

    @Override
    public void onItemDrag(int fromPosition, int toPosition) {
        Food prev = foodList.remove(fromPosition);
        foodList.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        adapter.notifyItemMoved(fromPosition, toPosition);
    }
}
