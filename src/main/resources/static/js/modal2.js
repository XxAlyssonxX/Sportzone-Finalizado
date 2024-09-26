$(document).ready(function() {
    // Função para abrir o modal
    function openModal(torneio) {
        // Atualiza o conteúdo do modal com as informações do torneio
        document.getElementById('tournament-info').innerHTML = `
            <strong>Nome:</strong> ${torneio.nome}<br>
            <strong>Data:</strong> ${torneio.data}<br>
            <strong>Local:</strong> ${torneio.cidade}, ${torneio.rua}, ${torneio.bairro}, Número: ${torneio.numero}<br>
            <strong>Faixa Etária:</strong> ${torneio.faixaEtaria}<br>
            <strong>Taxa:</strong> ${torneio.taxa}<br>
            <button id="inscrever-btn" class="btn-inscricao">Inscrever-se</button>
        `;
        
        // Exibe o modal
        document.getElementById('modal').style.display = 'block';

        // Adiciona o evento de clique para o botão de inscrição após ele ser renderizado no DOM
        const btnInscrever = document.getElementById('inscrever-btn');
        if (btnInscrever) {
            btnInscrever.addEventListener('click', function() {
                inscreverParticipante(torneio.id);
            });
        } else {
            console.error('Botão de inscrição não encontrado.');
        }
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

    // Configura o evento de fechamento do modal
    $(document).on('click', '.close', function() {
        closeModal();
    });

    // Função para realizar a inscrição
    function inscreverParticipante(torneioId) {
        const participanteId = 1;  // Id do participante logado
        fetch(`/inscricao/inscrever?participanteId=${participanteId}&torneioId=${torneioId}`, {
            method: 'POST'
        })
        .then(response => {
            // Verifica se a resposta é JSON
            const contentType = response.headers.get('content-type');
            if (contentType && contentType.includes('application/json')) {
                return response.json(); // Se for JSON, converte
            } else {
                return response.text(); // Caso contrário, trata como texto
            }
        })
        .then(data => {
            // Se data for um objeto JSON, trata como tal, senão, como texto
            if (typeof data === 'object') {
                alert('Inscrição realizada com sucesso!');
            } else {
                alert(data); // Pode ser uma string retornada pelo servidor
            }
            // Redireciona para a página de "Minhas Inscrições"
            window.location.href = "/inscricao.html";
        })
        .catch(error => {
            console.error('Erro durante a inscrição:', error);
            alert('Ocorreu um erro durante a inscrição. Por favor, tente novamente.');
        });
    }

    // Função para buscar torneios filtrados por classe
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
});
