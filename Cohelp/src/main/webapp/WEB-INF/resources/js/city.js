
var token = "1a6cc890e84c2eac701fc68515aee7aa4d6e7e0d";
var $city   = $("#city");
var $street = $("#street");
var $house  = $("#house");

// город и населенный пункт
$city.suggestions({
  token: token,
  type: "ADDRESS",
  hint: false,
  bounds: "city-settlement"
});

// улица
$street.suggestions({
  token: token,
  type: "ADDRESS",
  hint: false,
  bounds: "street",
  constraints: {
    locations: {
        region: "Татарстан",
        city: "Казань"
      },
  },
});

// дом
$house.suggestions({
  token: token,
  type: "ADDRESS",
  hint: false,
  noSuggestionsHint: false,
  bounds: "house",
  constraints: $street
});