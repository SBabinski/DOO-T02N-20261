function buscarClima() {
    const cidade = document.getElementById('inputCidade').value.trim();

    const divErro = document.getElementById('erro');
    const divResultado = document.getElementById('resultado');

    divErro.classList.add('escondido');
    divResultado.classList.add('escondido');

    if (cidade === '') {
        divErro.textContent = 'Por favor, digite o nome de uma cidade!';
        divErro.classList.remove('escondido');
        return;
    }

    fetch(`http://localhost:8080/api/clima?cidade=${encodeURIComponent(cidade)}`)
        .then(resposta => resposta.json())
        .then(dados => {

            if (dados.erro) {
                divErro.textContent = dados.erro;
                divErro.classList.remove('escondido');
                return;
            }

            document.getElementById('cidade').textContent = '📍 ' + dados.cidade;
            document.getElementById('tempAtual').textContent = dados.temperaturaAtual;
            document.getElementById('tempMin').textContent = dados.temperaturaMinima;
            document.getElementById('tempMax').textContent = dados.temperaturaMaxima;
            document.getElementById('umidade').textContent = dados.umidade + '%';
            document.getElementById('condicao').textContent = dados.condicao;
            document.getElementById('precipitacao').textContent = dados.precipitacao + ' mm';
            document.getElementById('vento').textContent = dados.velocidadeVento + ' km/h — ' + dados.direcaoVento;

            divResultado.classList.remove('escondido');
        })
        .catch(() => {
            divErro.textContent = 'Erro ao conectar com o servidor!';
            divErro.classList.remove('escondido');
        });
}

document.getElementById('inputCidade').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') buscarClima();
});