document.getElementById("submit").addEventListener("click", () => {

  fetch('/evaluate', {
    headers: {
      'Content-Type': 'application/json'
    },
    method: 'POST',
    body: JSON.stringify({
      expression: document.getElementById("input").value,
      param: document.getElementById("param").value,
      action: document.getElementById("select").value
    })
  }).then(res => {
    console.log(res);
    return res.json();
  }).then(data => {
    console.log(data);
    if (data.expression === null) {
      alert("Error");
    } else {
      let div = document.getElementById("result-list");
      let li = document.createElement("p");
      li.appendChild(document.createTextNode(data.expression));
      li.setAttribute("class", "result-item");
      div.insertBefore(li, div.firstElementChild);
    }

  }).catch(err => {
    console.log(err);
  });

});