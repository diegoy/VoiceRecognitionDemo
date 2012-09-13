package br.com.ponkanlab;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VoiceRecognitionDemoActivity extends Activity {

	private static final int REQUEST_CODE = 12310;
	private ListView wordListView;
	
	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        wordListView = (ListView) findViewById(R.id.word_list);
    }

    
	public void startVoiceRecognition(View v) {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition Demo...");
        startActivityForResult(intent, REQUEST_CODE);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			List<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			showArrayContentsInWordList(matches);
		}
		 super.onActivityResult(requestCode, resultCode, data);
	}


	private void showArrayContentsInWordList(List<String> matches) {
		wordListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
		        matches));
	}
    
}