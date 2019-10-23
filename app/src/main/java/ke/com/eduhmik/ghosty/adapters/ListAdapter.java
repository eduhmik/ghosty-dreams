package ke.com.eduhmik.ghosty.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ke.com.eduhmik.ghosty.R;
import ke.com.eduhmik.ghosty.models.Stories;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ClassHolder> {
    Context context;
    private List<Stories> list;


    public ListAdapter(Context context, List<Stories> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ListAdapter.ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ClassHolder holder, int position) {

        Stories model = list.get(position);
        holder.tvHeader.setText(model.getTitle());
        holder.tvMessage.setText(model.getMessage());

        Date dta = new Date(model.getTime());
        int formatedTextDate = Integer.parseInt(new SimpleDateFormat("dd").format(dta));
        int formatedMonth = Integer.parseInt(new SimpleDateFormat("MM").format(dta));
        int formaterdYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(dta));
        holder.tvTime.setText(formatedTextDate+"/"+formatedMonth+"/"+formaterdYear);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClassHolder extends RecyclerView.ViewHolder{

        TextView tvHeader, tvMessage, tvTime;
        ImageButton btShare, btDelete, btStar;
        public ClassHolder(View v) {
            super(v);
            tvHeader = v.findViewById(R.id.tv_topic);
            tvMessage = v.findViewById(R.id.tv_message);
            tvTime = v.findViewById(R.id.tv_time);


        }
    }
}
