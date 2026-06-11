const cityInput = document.getElementById('cityInput');
const searchBtn = document.getElementById('searchBtn');

searchBtn.addEventListener('click', fetchWeather);
cityInput.addEventListener('keydown', e => { if (e.key === 'Enter') fetchWeather(); });

function getWeatherEmoji(conditions) {
  const c = (conditions || '').toLowerCase();
  if (c.includes('tempestade') || c.includes('trovoada') || c.includes('thunder') || c.includes('storm')) return '⛈';
  if (c.includes('neve') || c.includes('granizo') || c.includes('snow') || c.includes('hail'))            return '❄️';
  if (c.includes('garoa') || c.includes('chuvisco') || c.includes('drizzle'))                             return '🌦';
  if (c.includes('chuva') || c.includes('rain') || c.includes('shower'))                                  return '🌧';
  if (c.includes('overcast') || c.includes('nublado') && !c.includes('parcialmente'))                     return '☁️';
  if (c.includes('parcialmente') || c.includes('partly') || c.includes('partial'))                        return '⛅';
  if (c.includes('neblina') || c.includes('névoa') || c.includes('fog') || c.includes('mist'))            return '🌫';
  if (c.includes('vento') || c.includes('wind'))                                                          return '🌬';
  if (c.includes('sol') || c.includes('ensolarado') || c.includes('clear') || c.includes('sunny'))       return '☀️';
  return '🌤';
}

async function fetchWeather() {
  const city = cityInput.value.trim();
  if (!city) {
    cityInput.classList.add('error-shake');
    setTimeout(() => cityInput.classList.remove('error-shake'), 1400);
    cityInput.focus();
    return;
  }

  showLoading();

  try {
    const response = await fetch('/api/weather?city=' + encodeURIComponent(city));
    const data = await response.json();

    if (!response.ok) {
      showError(data.error || 'Erro ao buscar o clima. Tente novamente.');
      return;
    }

    renderWeather(data);
  } catch {
    showError('Não foi possível conectar ao servidor. Verifique sua conexão e tente novamente.');
  }
}

function renderWeather(data) {
  hideAll();

  document.getElementById('cityName').textContent        = data.city;
  document.getElementById('resolvedAddress').textContent = data.resolvedAddress || data.city;
  document.getElementById('weatherEmoji').textContent    = getWeatherEmoji(data.conditions);
  document.getElementById('currentTemp').textContent     = fmt1(data.currentTemp);
  document.getElementById('conditions').textContent      = data.conditions || '—';
  document.getElementById('tempMaxMin').textContent      = fmt1(data.tempMax) + '° / ' + fmt1(data.tempMin) + '°';
  document.getElementById('humidity').textContent        = Math.round(data.humidity) + '%';
  document.getElementById('precip').textContent          = data.precip > 0 ? fmt1(data.precip) + ' mm' : 'Sem precipitação';
  document.getElementById('wind').textContent            = fmt1(data.windSpeed) + ' km/h ' + data.windCardinal;

  document.getElementById('weatherCard').classList.add('visible');
}

function showError(message) {
  hideAll();
  document.getElementById('errorMessage').textContent = message;
  document.getElementById('errorBox').classList.add('visible');
}

function showLoading() {
  hideAll();
  document.getElementById('loading').classList.add('visible');
}

function hideAll() {
  ['loading', 'errorBox', 'weatherCard'].forEach(id =>
    document.getElementById(id).classList.remove('visible')
  );
}

function fmt1(v) { return Number(v).toFixed(1); }
