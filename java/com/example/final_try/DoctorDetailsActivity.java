package com.example.final_try;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name: Seema Aparaj", "Hospital Address : Yelhanka", "Exp : 5yrs", "Mobile No: 9638527410", "600"},
                    {"Doctor Name: Siddhanth", "Hospital Address : Singanayakanahalli", "Exp : 15yrs", "Mobile No: 9876543210", "500"},
                    {"Doctor Name: Santhosh G", "Hospital Address : Rajajinagar", "Exp : 8yrs", "Mobile No: 6549873210", "300"},
                    {"Doctor Name: Ashwini Raj", "Hospital Address : Vijayanagar", "Exp : 6yrs", "Mobile No: 895623741", "500"},
                    {"Doctor Name: Yogin Ramesh", "Hospital Address : Rajunkunte", "Exp : 7yrs", "Mobile No: 951023674", "800"},
            };

    private String[][] doctor_details2 =
            {
                    {"Doctor Name: Pramod Rao", "Hospital Address : Yelhanka", "Exp : 5yrs", "Mobile No: 9638527410", "600"},
                    {"Doctor Name: Tarun", "Hospital Address : Singanayakanahalli", "Exp : 15yrs", "Mobile No: 9876543210", "500"},
                    {"Doctor Name: Varun", "Hospital Address : Rajajinagar", "Exp : 8yrs", "Mobile No: 6549873210", "300"},
                    {"Doctor Name: Preethi Rao", "Hospital Address : Vijayanagar", "Exp : 6yrs", "Mobile No: 895623741", "500"},
                    {"Doctor Name: Kishan Rangadol", "Hospital Address : Rajunkunte", "Exp : 7yrs", "Mobile No: 951023674", "800"},
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name: Rachana", "Hospital Address : Yelhanka", "Exp : 5yrs", "Mobile No: 9638527410", "600"},
                    {"Doctor Name: Ajay", "Hospital Address : Singanayakanahalli", "Exp : 15yrs", "Mobile No: 9876543210", "500"},
                    {"Doctor Name: Varshitha", "Hospital Address : Rajajinagar", "Exp : 8yrs", "Mobile No: 6549873210", "300"},
                    {"Doctor Name: Kiran Kumar", "Hospital Address : Vijayanagar", "Exp : 6yrs", "Mobile No: 895623741", "500"},
                    {"Doctor Name: Shwetha", "Hospital Address : Rajunkunte", "Exp : 7yrs", "Mobile No: 951023674", "800"},
            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name: Gowthami", "Hospital Address : Yelhanka", "Exp : 5yrs", "Mobile No: 9638527410", "600"},
                    {"Doctor Name: Harsha Vardhan", "Hospital Address : Singanayakanahalli", "Exp : 15yrs", "Mobile No: 9876543210", "500"},
                    {"Doctor Name: Shreyas", "Hospital Address : Rajajinagar", "Exp : 8yrs", "Mobile No: 6549873210", "300"},
                    {"Doctor Name: Gagan", "Hospital Address : Vijayanagar", "Exp : 6yrs", "Mobile No: 895623741", "500"},
                    {"Doctor Name: Suresh Kumar", "Hospital Address : Rajunkunte", "Exp : 7yrs", "Mobile No: 951023674", "800"},
            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name: Hamsaveni", "Hospital Address : Yelhanka", "Exp : 5yrs", "Mobile No: 9638527410", "600"},
                    {"Doctor Name: Bindumathi", "Hospital Address : Singanayakanahalli", "Exp : 15yrs", "Mobile No: 9876543210", "500"},
                    {"Doctor Name: Arun Singh", "Hospital Address : Rajajinagar", "Exp : 8yrs", "Mobile No: 6549873210", "300"},
                    {"Doctor Name: Deepak Kumar", "Hospital Address : Vijayanagar", "Exp : 6yrs", "Mobile No: 895623741", "500"},
                    {"Doctor Name: Darshan", "Hospital Address : Rajunkunte", "Exp : 7yrs", "Mobile No: 951023674", "800"},
            };

    TextView tv ;
    Button btn;
    String[][] doctor_details ={};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physisicans")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons fees: "+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}