package sg.edu.rp.c346.round3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EntryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is entry fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}