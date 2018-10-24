package artn.lab5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Main extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv;
    private EditText et;
    private ArrayAdapter<String> arAdap;
    private int curPos = -1;
    private View toUncheck = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        arAdap = new ArrayAdapter<String>(this, R.layout.list_lay);
        lv = (ListView) findViewById(R.id.lstV);
        lv.setAdapter(arAdap);
        et = (EditText) findViewById(R.id.edTxt);
        lv.setOnItemClickListener(this);
    }

    public void Add(View view) {
        if (!et.toString().isEmpty()) {
            arAdap.add(et.getText().toString());
            curPos = arAdap.getCount();
        }
    }

    public void Del(View view) {
        if (curPos >= 0 && curPos < arAdap.getCount()) {
            String s = arAdap.getItem(curPos);
            arAdap.remove(s);
            toUncheck.setBackgroundColor(getResources().getColor(R.color.transparent));
            toUncheck = null;
        }
    }

    public void Edit(View view) {
        if (curPos >= 0 && curPos < arAdap.getCount()) {
            String s = arAdap.getItem(curPos);
            arAdap.remove(s);
            arAdap.insert(et.getText().toString(), curPos);
        }
    }

    public void Clr(View view) {
        arAdap.clear();
        toUncheck= null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = arAdap.getItem(position);
        et.setText(s);
        if (toUncheck != null)
            toUncheck.setBackgroundColor(getResources().getColor(R.color.transparent));
        curPos = position;
        toUncheck = view;
        view.setBackgroundColor(getResources().getColor(R.color.selected));
    }
}
