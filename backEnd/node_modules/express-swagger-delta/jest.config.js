// For a detailed explanation regarding each configuration property, visit:
// https://jestjs.io/docs/en/configuration.html

module.exports = {
  clearMocks: true,
  collectCoverage: true,
  collectCoverageFrom: ['example/**/*.js'],
  coveragePathIgnorePatterns: ['api.js'],
  coverageThreshold: {
    global: {
      statements: 90,
      branches: 80,
      functions: 80,
      lines: 90,
    },
  },
  coverageProvider: 'babel',
  setupFiles: ['./jest.setup.js'],
  testMatch: ['**/__tests__/**/*.test.js'],
  testPathIgnorePatterns: ['\\\\node_modules\\\\'],
  coverageDirectory: './__tests__/coverage',
  coverageReporters: ['json-summary', 'text', 'lcov'],
  testTimeout: 30000,
  transform: {
    '.(js|jsx|ts|tsx)': '@sucrase/jest-plugin',
  },
};
