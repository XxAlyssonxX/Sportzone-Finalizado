$(document).ready(function() {
    // Função para buscar torneios do servidor
    function buscarTorneios(classe = 'todos') {
        $.ajax({
            url: "http://localhost:8080/Torneio/", // Altere para a URL do seu endpoint
            method: "GET",
            success: function(data) {
                renderizarTorneios(data, classe);
            },
            error: function() {
                alert("Erro ao carregar os torneios.");
            }
        });
    }

    // Função para renderizar os torneios na página
    function renderizarTorneios(torneios, classe = 'todos') {
    const container = $('.tournament-grid');
    container.empty(); // Limpa o conteúdo anterior

    const torneiosFiltrados = classe === 'todos' ? torneios : torneios.filter(torneio => torneio.classe === classe);

    torneiosFiltrados.forEach(torneio => {
        const torneioDiv = $(
            `<div class="tournament">
                <img src="${torneio.imagem}" alt="${torneio.nome}">
                <h3>${torneio.nome}</h3>
                <p>Data: ${torneio.data}</p>
                <p>Taxa: ${torneio.taxa}</p>
                <a href="#" class="btn">Mais Informações</a>
            </div>`
        );
        
        // Armazena os dados do torneio no botão para uso posterior
        torneioDiv.find('.btn').data('torneio', torneio);

        container.append(torneioDiv);
    });
}

    // Chama a função ao carregar a página para exibir todos os torneios inicialmente
    buscarTorneios();
});
