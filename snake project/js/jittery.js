//AQUESTA FUNCIÓ NOMÉS SERVEIX PER L'EFECTE DE LES LLETRES DE LA INTERFÍCIE D'INICI DE JOC

$(function () {
  var $jittery = $('.jittery'),
    aText = $jittery.text().split(''),
    letters = '';

  for (var i = 0; i < aText.length; i++) {
    letters += '<span>' + aText[i] + '</span>';
  }

  $jittery.empty().append(letters);

  $.each($('span', $jittery), function (i) {
    $(this).css('animation-delay', '-' + i + '70ms');
  });
});