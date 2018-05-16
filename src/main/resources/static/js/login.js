console.log('Page loaded.');

if (location.search === '?failed') {
  $('#failed-message').removeClass('hidden');
}

if (location.search === '?password-changed') {
  $('#password-changed-message').removeClass('hidden');

}