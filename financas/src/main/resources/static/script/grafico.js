const diversao = document.getElementById("diversao").value;    
const comida = document.getElementById("comida").value; 
const limpeza = document.getElementById("limpeza").value; 
const estudo = document.getElementById("estudo").value; 
const saude = document.getElementById("saude").value; 
const locomocao = document.getElementById("locomocao").value; 
const presente = document.getElementById("presente").value; 
const bebida = document.getElementById("bebida").value; 
const casa = document.getElementById("casa").value; 
const vestuario = document.getElementById("vestuario").value; 
const outros = document.getElementById("outros").value; 
           
  
            const vardiversao = diversao/1;
            const varcomida = comida/1;
            const varlimpeza = limpeza/1;
            const varestudo = estudo/1;
            const varsaude = saude/1;
            const varlocomocao = locomocao/1;
            const varpresente = presente/1;
            const varbebida = bebida/1;
            const varcasa = casa/1;
            const varvestuario = vestuario/1;
            const varoutros= outros/1;
          google.charts.load("current", {packages:["corechart"]});
          google.charts.setOnLoadCallback(drawChart);
          function drawChart() {
            var data = google.visualization.arrayToDataTable([
              ['Task', 'Hours per Day'],
              ['Diversão R$:- '+ vardiversao.toFixed(2),     vardiversao],
              ['Comida R$:- '+ varcomida.toFixed(2),      varcomida],
              ['Limpeza R$:- '+ varlimpeza.toFixed(2),  varlimpeza],
              ['EstudoR$:- '+ varestudo.toFixed(2), varestudo],
              ['SaudeR$:- '+ varsaude.toFixed(2),    varsaude],
              ['LocomoçãoR$:- '+ varlocomocao.toFixed(2),    varlocomocao],
              ['PresentesR$:- '+ varpresente.toFixed(2),    varpresente],
              ['BebidaR$:- '+ varbebida.toFixed(2),    varbebida],
              ['CasaR$:- '+ varcasa.toFixed(2),    varcasa],
              ['VestuarioR$:- '+ varvestuario.toFixed(2),    varvestuario],
              ['Outros diversosR$:- '+ varoutros.toFixed(2),    varoutros]
            ]);
    
            var options = {
              title: 'Grafico de total de gastos por Area ',
              is3D: true,
            };
    
            var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
            chart.draw(data, options);
          }
