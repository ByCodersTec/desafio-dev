import _extends from "@babel/runtime/helpers/esm/extends";
import _objectWithoutProperties from "@babel/runtime/helpers/esm/objectWithoutProperties";
import { deepmerge } from '@mui/utils';
import { colorChannel } from '@mui/system';
import createThemeWithoutVars from './createTheme';
import createPalette from './createPalette';
export var defaultOpacity = {
  active: 0.54,
  hover: 0.04,
  selected: 0.08,
  disabled: 0.26,
  focus: 0.12
};

function createTheme() {
  var _colorSchemesInput$li, _colorSchemesInput$da;

  var options = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

  var _options$colorSchemes = options.colorSchemes,
      colorSchemesInput = _options$colorSchemes === void 0 ? {} : _options$colorSchemes,
      _options$opacity = options.opacity,
      opacityInput = _options$opacity === void 0 ? {} : _options$opacity,
      input = _objectWithoutProperties(options, ["colorSchemes", "opacity"]); // eslint-disable-next-line prefer-const


  var _createThemeWithoutVa = createThemeWithoutVars(_extends({}, input, colorSchemesInput.light && {
    palette: (_colorSchemesInput$li = colorSchemesInput.light) == null ? void 0 : _colorSchemesInput$li.palette
  })),
      lightPalette = _createThemeWithoutVa.palette,
      muiTheme = _objectWithoutProperties(_createThemeWithoutVa, ["palette"]);

  var _createThemeWithoutVa2 = createThemeWithoutVars({
    palette: _extends({
      mode: 'dark'
    }, (_colorSchemesInput$da = colorSchemesInput.dark) == null ? void 0 : _colorSchemesInput$da.palette)
  }),
      darkPalette = _createThemeWithoutVa2.palette;

  colorSchemesInput.light = {
    palette: lightPalette
  };
  colorSchemesInput.dark = {
    palette: darkPalette
  };
  var colorSchemes = {};
  Object.keys(colorSchemesInput).forEach(function (key) {
    var palette = createPalette(colorSchemesInput[key].palette);
    Object.keys(palette).forEach(function (color) {
      var colors = palette[color];

      if (colors.main) {
        palette[color].mainChannel = colorChannel(colors.main);
      }

      if (colors.light) {
        palette[color].lightChannel = colorChannel(colors.light);
      }

      if (colors.dark) {
        palette[color].darkChannel = colorChannel(colors.dark);
      }

      if (colors.primary) {
        palette[color].primaryChannel = colorChannel(colors.primary);
      }

      if (colors.secondary) {
        palette[color].secondaryChannel = colorChannel(colors.secondary);
      }

      if (colors.disabled) {
        palette[color].disabledChannel = colorChannel(colors.disabled);
      }
    });
    colorSchemes[key] = {
      palette: palette
    };
  });

  var opacity = _extends({}, defaultOpacity, opacityInput);

  muiTheme.colorSchemes = colorSchemes;
  muiTheme.opacity = opacity;

  for (var _len = arguments.length, args = new Array(_len > 1 ? _len - 1 : 0), _key = 1; _key < _len; _key++) {
    args[_key - 1] = arguments[_key];
  }

  muiTheme = args.reduce(function (acc, argument) {
    return deepmerge(acc, argument);
  }, muiTheme);
  return muiTheme;
}

export default createTheme;