function abrirModal() {
  document.getElementById('modal').style.top = "0";
  document.getElementById('modall').style.display = "flex";
}


function fecharModal() {
  document.getElementById('modal').style.top = "-100%";
  document.getElementById('modall').style.display = "";
}


function calculate() {
let tipos = document.querySelector("#tipo");
let tipe = tipos.options[tipos.selectedIndex];
let type = tipe.value;

let portes = document.querySelector("#porte");
let porte = portes.options[portes.selectedIndex];
let port = porte.value;

let raca = document.querySelector("#raca");
let racas = raca.options[raca.selectedIndex];
let racaa = racas.value;

console.log(type)
console.log(port)
console.log(racaa)
var text = "";

if (type == "dog") {
if (port == "big") {
if (racaa == "raca") {
  text = "Em media R$420,00 mensais";
} else {
  text = "Em media R$380,00 mensais";
}
} else {
if (port == "medium") {
  if (racaa = "raca") {
    text = "Em media R$380,00 mensais";
  } else {
    text = "Em media R$340,00 mensais";
  }
} else {
  if (racaa == "raca") {
    text = "Em media R$280,00 mensais";
  } else {
    text = "Em media R$240,00 mensais";
  }
}
}
} else {
if (port == "big") {
if (racaa == "raca") {
  text = "Em media R$200,00 mensais";
} else {
  text = "Em media R$180,00 mensais";
}
} else {
if (port == "medium") {
  if (racaa == "raca") {
    text = "Em media R$150,00 mensais";
  } else {
    text = "Em media R$135,00 mensais";
  }
} else {
  if (racaa == "raca") {
    text = "Em media R$120,00 mensais";
  } else {
    text = "Em media R$90,00 mensais";
  }
}
}
document.getElementById("text_area").innerText = text
}
}
