package br.com.alisonmoura.hobbit.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.alisonmoura.hobbit.Models.Usuario;
import br.com.alisonmoura.hobbit.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alisonmoura on 25/10/15.
 */
public class UserListAdapter extends ArrayAdapter<Usuario> {

    public UserListAdapter(Context context, List<Usuario> usuarioList) {
        super(context, R.layout.item_user_list, usuarioList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.item_user_list, null);
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Usuario usuario = getItem(position);
        if (usuario != null) {
            holder.nome.setText(usuario.getNome());
            if (usuario.getFoto() > 0)
                holder.foto.setImageResource(usuario.getFoto());
            else holder.foto.setImageResource(R.drawable.default_user_image);
        }

        return convertView;
    }

    public class ViewHolder {

        @Bind(R.id.imageViewItemUserListProfileImage)
        ImageView foto;

        @Bind(R.id.textViewItemUserListUserName)
        TextView nome;

    }
}
