"use strict";

var _interopRequireDefault = require("@babel/runtime/helpers/interopRequireDefault");

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.defaultOpacity = exports.default = void 0;

var _extends2 = _interopRequireDefault(require("@babel/runtime/helpers/extends"));

var _objectWithoutPropertiesLoose2 = _interopRequireDefault(require("@babel/runtime/helpers/objectWithoutPropertiesLoose"));

var _utils = require("@mui/utils");

var _system = require("@mui/system");

var _createTheme = _interopRequireDefault(require("./createTheme"));

var _createPalette = _interopRequireDefault(require("./createPalette"));

const _excluded = ["colorSchemes", "opacity"],
      _excluded2 = ["palette"];
const defaultOpacity = {
  active: 0.54,
  hover: 0.04,
  selected: 0.08,
  disabled: 0.26,
  focus: 0.12
};
exports.defaultOpacity = defaultOpacity;

function createTheme(options = {}, ...args) {
  var _colorSchemesInput$li, _colorSchemesInput$da;

  const {
    colorSchemes: colorSchemesInput = {},
    opacity: opacityInput = {}
  } = options,
        input = (0, _objectWithoutPropertiesLoose2.default)(options, _excluded); // eslint-disable-next-line prefer-const

  let _createThemeWithoutVa = (0, _createTheme.default)((0, _extends2.default)({}, input, colorSchemesInput.light && {
    palette: (_colorSchemesInput$li = colorSchemesInput.light) == null ? void 0 : _colorSchemesInput$li.palette
  })),
      {
    palette: lightPalette
  } = _createThemeWithoutVa,
      muiTheme = (0, _objectWithoutPropertiesLoose2.default)(_createThemeWithoutVa, _excluded2);

  const {
    palette: darkPalette
  } = (0, _createTheme.default)({
    palette: (0, _extends2.default)({
      mode: 'dark'
    }, (_colorSchemesInput$da = colorSchemesInput.dark) == null ? void 0 : _colorSchemesInput$da.palette)
  });
  colorSchemesInput.light = {
    palette: lightPalette
  };
  colorSchemesInput.dark = {
    palette: darkPalette
  };
  const colorSchemes = {};
  Object.keys(colorSchemesInput).forEach(key => {
    const palette = (0, _createPalette.default)(colorSchemesInput[key].palette);
    Object.keys(palette).forEach(color => {
      const colors = palette[color];

      if (colors.main) {
        palette[color].mainChannel = (0, _system.colorChannel)(colors.main);
      }

      if (colors.light) {
        palette[color].lightChannel = (0, _system.colorChannel)(colors.light);
      }

      if (colors.dark) {
        palette[color].darkChannel = (0, _system.colorChannel)(colors.dark);
      }

      if (colors.primary) {
        palette[color].primaryChannel = (0, _system.colorChannel)(colors.primary);
      }

      if (colors.secondary) {
        palette[color].secondaryChannel = (0, _system.colorChannel)(colors.secondary);
      }

      if (colors.disabled) {
        palette[color].disabledChannel = (0, _system.colorChannel)(colors.disabled);
      }
    });
    colorSchemes[key] = {
      palette
    };
  });
  const opacity = (0, _extends2.default)({}, defaultOpacity, opacityInput);
  muiTheme.colorSchemes = colorSchemes;
  muiTheme.opacity = opacity;
  muiTheme = args.reduce((acc, argument) => (0, _utils.deepmerge)(acc, argument), muiTheme);
  return muiTheme;
}

var _default = createTheme;
exports.default = _default;