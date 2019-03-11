package com.rafael.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rafael.agenda.dao.AlunoDAO;
import com.rafael.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {
    public FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            helper.preencheFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_formulario_ok:
                //Criei um field para o helper
                //OBS: field parece composição, ou uma variavel global
                //OBS 2: o objeto Aluno recebe um Aluno do FormularioHelper através do método pegaAluno
                Aluno aluno = helper.pegaAluno();

                AlunoDAO dao = new AlunoDAO(this);
                if(aluno.getId() != 0){
                    dao.altera(aluno);
                }else{
                    dao.insereAluno(aluno);
                }

                Toast.makeText(FormularioActivity.this, "Aluno " +aluno.getNome()+ " Salvo!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
