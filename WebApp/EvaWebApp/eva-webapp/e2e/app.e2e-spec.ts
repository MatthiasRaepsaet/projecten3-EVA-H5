import { EvaWebappPage } from './app.po';

describe('eva-webapp App', () => {
  let page: EvaWebappPage;

  beforeEach(() => {
    page = new EvaWebappPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
