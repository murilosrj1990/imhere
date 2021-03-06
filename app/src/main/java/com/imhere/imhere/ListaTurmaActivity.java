package com.imhere.imhere;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaTurmaActivity extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turma);

        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDadosTurma();

        String[] nomeCampos = new String[]{CriaBanco.ID_TURMA, CriaBanco.NOME_TURMA};
        int[] idViews = new int[]{R.id.idPessoa, R.id.nomePessoa};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_nomes, cursor,nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID_TURMA));
                Intent intent = new Intent(ListaTurmaActivity.this, AlteraTurmaActivity.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
        FloatingActionButton addTurma;
        addTurma = findViewById(R.id.faButton);
        addTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ListaTurmaActivity.this ,CadastroTurmaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
