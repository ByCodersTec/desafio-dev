import sinon from 'sinon';

// CONFIG JEST
const time = {
  setTimeout: () => {
    return global.setTimeout.apply(global, arguments);
  },
};

sinon.stub(time, 'setTimeout');

jest.setTimeout(30000);

global.console = {
  ...console,
  log: jest.fn(),
  debug: jest.fn(),
  warn: jest.fn(),
};
