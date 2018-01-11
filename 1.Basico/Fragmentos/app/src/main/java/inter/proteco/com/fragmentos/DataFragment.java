package inter.proteco.com.fragmentos;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class DataFragment extends Fragment {


    private DataListener mListener;
    private EditText editText;
    private Button btnSend;

    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        //logica para capturar elementos

        editText = (EditText) view.findViewById(R.id.editTextData);
        btnSend = (Button) view.findViewById(R.id.buttonSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textToSend = editText.getText().toString();
                mListener.sendData(textToSend);
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DataListener) context;

        } catch (Exception e) {

        }

    }

    public interface DataListener {
        void sendData(String text);
    }


    @Override
    public void onDetach () {
        super.onDetach();
        mListener = null;
    }
}
