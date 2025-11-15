package com.example.notas
import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notas.ui.theme.NotasTheme
import com.example.notas.Tarefa



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotasTheme {
                DesafioTodoList()
            }
        }
    }
}
@Composable
fun DesafioTodoList(){

    var textoTarefa by remember { mutableStateOf("") }
    val listaDeTarefas = remember { mutableStateListOf<Tarefa>() }

//layout
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding ->
        Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Lista de Tarefas",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp) // Adiciona um espaço extra no topo, se desejar

        )

        Row(
            modifier = Modifier.padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            OutlinedTextField(
                value = textoTarefa,
                onValueChange = { novoTexto -> textoTarefa = novoTexto },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Digite uma tarefa") },
                label = { Text("Nova Tarefa") }
            )
            Button(
                onClick = {
                    if (textoTarefa.isNotBlank()) {
                        listaDeTarefas.add(Tarefa(
                            nome = textoTarefa,
                            concluida = mutableStateOf(false)
                        ))
                        textoTarefa = ""
                    }
                },

                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
            ) {
                Text("Adicionar")
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listaDeTarefas) { tarefa ->
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,

                ) {
                    Checkbox(
                        checked = tarefa.concluida.value,
                        onCheckedChange = { isChecked ->
                            tarefa.concluida.value = isChecked
                        }
                    )
                    Text(
                        text = tarefa.nome,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        Button(
            modifier = Modifier.padding(vertical = 16.dp),
            onClick = {
                listaDeTarefas.removeAll { it.concluida.value }
            }
        ) {
            Text("Limpar concluídas")
        }
    }
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotasTheme {
        DesafioTodoList()
    }
}