package br.edu.ifsc.launcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder> {
    private List<AppInfo> appList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView appNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            appNameTextView = itemView.findViewById(R.id.appNameTextView);
        }
    }

    public AppListAdapter(List<AppInfo> appList, Context context) {
        this.appList = appList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AppInfo appInfo = appList.get(position);
        holder.appNameTextView.setText(appInfo.getAppName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp(appInfo.getPackageName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    private void launchApp(String packageName) {
        PackageManager pm = context.getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);
        if (intent != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Unable to launch the app", Toast.LENGTH_SHORT).show();
        }
    }
}