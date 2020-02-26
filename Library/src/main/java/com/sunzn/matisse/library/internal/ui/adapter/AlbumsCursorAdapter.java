package com.sunzn.matisse.library.internal.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunzn.matisse.library.R;
import com.sunzn.matisse.library.internal.entity.Album;
import com.sunzn.matisse.library.internal.entity.SelectionSpec;
import com.sunzn.matisse.library.internal.ui.widget.RoundedRectangleImageView;

import java.io.File;

public class AlbumsCursorAdapter extends CursorAdapter<AlbumsCursorAdapter.ViewHolder> {

    private Context mContext;

    private final Drawable mPlaceholder;

    private static OnItemSelectedListener mOnItemSelectedListener;

    public interface OnItemSelectedListener {

        void onItemSelected(int position, String name);

    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        mOnItemSelectedListener = listener;
    }

    public AlbumsCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{R.attr.album_thumbnail_placeholder});
        mPlaceholder = ta.getDrawable(0);
        ta.recycle();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        Album album = Album.valueOf(cursor);
        holder.getName().setText(album.getDisplayName(mContext));
        holder.getCount().setText(String.valueOf(album.getCount()));

        // do not need to load animated Gif
        SelectionSpec.getInstance().imageEngine.loadThumbnail(mContext, mContext.getResources().getDimensionPixelSize(R.dimen.media_grid_size), mPlaceholder, holder.getCover(), Uri.fromFile(new File(album.getCoverPath())));
    }

    @Override
    protected void onContentChanged() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_list_item, parent, false);
        return new ViewHolder(v);
    }

    static void onItemSelected() {

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final RoundedRectangleImageView cover;
        private final TextView name;
        private final TextView count;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemSelectedListener != null) {
                        int position = getAdapterPosition();
                        Cursor cursor = getCursor();
                        cursor.moveToPosition(position);
                        Album album = Album.valueOf(cursor);
                        String displayName = album.getDisplayName(v.getContext());
                        mOnItemSelectedListener.onItemSelected(position, displayName);
                    }
                }
            });

            cover = itemView.findViewById(R.id.album_cover);
            name = itemView.findViewById(R.id.album_name);
            count = itemView.findViewById(R.id.album_media_count);
        }

        public RoundedRectangleImageView getCover() {
            return cover;
        }

        public TextView getName() {
            return name;
        }

        public TextView getCount() {
            return count;
        }

    }

}
