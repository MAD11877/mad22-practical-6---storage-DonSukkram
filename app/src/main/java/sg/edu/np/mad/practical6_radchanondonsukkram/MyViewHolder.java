package sg.edu.np.mad.practical6_radchanondonsukkram;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView userIconImage;
    TextView txtUserName;
    TextView txtUserDesc;

    public MyViewHolder(View itemView) {
        super(itemView);
        userIconImage = itemView.findViewById(R.id.userIcon);
        txtUserName = itemView.findViewById(R.id.userName);
        txtUserDesc = itemView.findViewById(R.id.userDescription);
    }
}
