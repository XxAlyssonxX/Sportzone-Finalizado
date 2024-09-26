$(document).ready(function() {
    // Função para abrir o modal
    function openModal(torneio) {
        // Atualiza o conteúdo do modal com as informações do torneio
        document.getElementById('tournament-info').innerHTML = `
            <strong>Nome:</strong> ${torneio.nome}<br>
            <strong>Data:</strong> ${torneio.data}<br>
            <strong>Local:</strong> ${torneio.cidade}, ${torneio.rua}, ${torneio.bairro}, Número: ${torneio.numero}<br>
            <strong>Faixa Etária:</strong> ${torneio.faixaEtaria}<br>
            <strong>Taxa:</strong> ${torneio.taxa}
        `;
        
        // Exibe o modal
        document.getElementById('modal').style.display = 'block';
    }

    // Função para fechar o modal
    function closeModal() {
        document.getElementById('modal').style.display = 'none';
    }

    // Fecha o modal quando o usuário clica fora dele
    window.onclick = function(event) {
        const modal = document.getElementById('modal');
        if (event.target === modal) {
            closeModal();
        }
    };

    // Usar delegação de eventos para o botão "Mais Informações"
    $(document).on('click', '.btn', function(e) {
        e.preventDefault();

        // Obtém os dados do torneio a partir do botão
        const torneio = $(this).data('torneio');
        openModal(torneio);
    });

    // Configura o evento de fechamento do modal (delegado também)
    $(document).on('click', '.close', function() {
        closeModal();
    });
});
